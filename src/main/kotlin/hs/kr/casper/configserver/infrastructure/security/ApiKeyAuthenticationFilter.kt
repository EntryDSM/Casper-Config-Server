package hs.kr.casper.configserver.infrastructure.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class ApiKeyAuthenticationFilter(
    private val apiKeyProperties: ApiKeyProperties
) : OncePerRequestFilter() {
    
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestPath = request.requestURI
        if (isPublicEndpoint(requestPath)) {
            filterChain.doFilter(request, response)
            return
        }
        
        val apiKey = extractApiKeyFromRequest(request)
        
        if (apiKey == null || apiKey != apiKeyProperties.accessKey) {

            return
        }
        
        filterChain.doFilter(request, response)
    }
    
    private fun isPublicEndpoint(path: String): Boolean {
        val publicPaths = listOf(
            "/actuator/health",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/api-docs",
            "/v3/api-docs"
        )
        
        // Spring Cloud Config paths
        val configPathPattern = Regex("^/[^/]+/[^/]+(/[^/]+)?$")
        
        // Swagger UI paths
        val swaggerPathPatterns = listOf(
            Regex("^/swagger-ui/.*$"),
            Regex("^/v3/api-docs.*$")
        )
        
        return publicPaths.any { path == it } || 
               configPathPattern.matches(path) ||
               swaggerPathPatterns.any { it.matches(path) }
    }
    
    private fun extractApiKeyFromRequest(request: HttpServletRequest): String? {
        return request.getHeader("X-API-Key") ?: request.getParameter("apiKey")
    }
}