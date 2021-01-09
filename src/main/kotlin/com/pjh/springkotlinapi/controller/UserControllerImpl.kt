package com.pjh.springkotlinapi.controller

import com.pjh.springkotlinapi.controller.UserControllerImpl.Companion.BASE_USER_URI
import com.pjh.springkotlinapi.dto.AddUserRequest
import com.pjh.springkotlinapi.dto.UpdateUserRequest
import com.pjh.springkotlinapi.dto.UserResponse
import com.pjh.springkotlinapi.service.UserManagementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(value = [BASE_USER_URI])
class UserControllerImpl(private val userManagementService: UserManagementService) : UserController {

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseEntity<UserResponse>? {
        val userResponse = this.userManagementService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(userResponse)
    }

    @GetMapping
    override fun findAll(): ResponseEntity<List<UserResponse>> = ResponseEntity.ok(this.userManagementService.findAll())

    @PostMapping
    override fun save(addUserRequest: AddUserRequest): ResponseEntity<UserResponse> {
        val userResponse = this.userManagementService.save(addUserRequest)
        return ResponseEntity.created(URI.create(BASE_USER_URI.plus("/${userResponse.id}")))
                .body(userResponse)
    }

    @PutMapping
    override fun update(updateUserRequest: UpdateUserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(this.userManagementService.update(updateUserRequest))
    }

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        this.userManagementService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    companion object{
        const val BASE_USER_URI: String = "/api/v1/user"
    }
}