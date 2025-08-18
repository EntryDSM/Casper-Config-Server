package hs.kr.casper.configserver.infrastructure.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Public endpoints - skip JWT validation
        val requestPath = request.requestURI
        if (isPublicEndpoint(requestPath)) {
            filterChain.doFilter(request, response)
            return
        }
        
        val token = extractTokenFromRequest(request)
        
        if (token != null && jwtTokenProvider.validateToken(token)) {
            val username = jwtTokenProvider.getUsername(token)
            val authorities = jwtTokenProvider.getAuthorities(token)
                .map { SimpleGrantedAuthority("ROLE_$it") }
            
            val authentication = UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
            ).apply {
                details = WebAuthenticationDetailsSource().buildDetails(request)
            }
            
            SecurityContextHolder.getContext().authentication = authentication
        }
        
        filterChain.doFilter(request, response)
    }
    
    private fun isPublicEndpoint(path: String): Boolean {
        val publicPaths = listOf(
            "/auth/token",
            "/actuator/health"
        )
        
        // Spring Cloud Config paths
        val configPathPattern = Regex("^/[^/]+/[^/]+(/[^/]+)?$")
        
        return publicPaths.any { path == it } || configPathPattern.matches(path)
    }
    
    private fun extractTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken?.startsWith("Bearer ") == true) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}