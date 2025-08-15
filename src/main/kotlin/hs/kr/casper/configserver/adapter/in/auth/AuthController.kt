package hs.kr.casper.configserver.adapter.`in`.auth

import hs.kr.casper.configserver.adapter.`in`.auth.dto.RegisterRequest
import hs.kr.casper.configserver.adapter.`in`.auth.dto.RegisterResponse
import hs.kr.casper.configserver.adapter.`in`.auth.dto.TokenRequest
import hs.kr.casper.configserver.adapter.`in`.auth.dto.TokenResponse
import hs.kr.casper.configserver.application.user.port.`in`.AuthenticateUserUseCase
import hs.kr.casper.configserver.application.user.port.`in`.RegisterUserUseCase
import hs.kr.casper.configserver.infrastructure.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) {
    
    @PostMapping("/token")
    fun generateToken(@RequestBody request: TokenRequest): ResponseEntity<TokenResponse> {
        if (request.username.isBlank() || request.password.isBlank()) {
            return ResponseEntity.badRequest().build()
        }
        
        val authenticatedUser = authenticateUserUseCase.authenticate(request.username, request.password)
            ?: return ResponseEntity.status(401).build()
        
        authenticateUserUseCase.updateLastLogin(authenticatedUser)
        
        val token = jwtTokenProvider.generateToken(
            username = authenticatedUser.username,
            authorities = listOf(authenticatedUser.role.authority)
        )
        
        return ResponseEntity.ok(TokenResponse(token))
    }
    
    @PostMapping("/register")
    @PreAuthorize("hasRole('CONFIG_ADMIN')")
    fun registerUser(@RequestBody request: RegisterRequest): ResponseEntity<RegisterResponse> {
        val registeredUser = registerUserUseCase.registerUser(
            username = request.username,
            password = request.password,
            email = request.email,
            role = request.role
        )
        
        val response = RegisterResponse(
            id = registeredUser.id,
            username = registeredUser.username,
            email = registeredUser.email,
            role = registeredUser.role,
            enabled = registeredUser.enabled
        )
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}