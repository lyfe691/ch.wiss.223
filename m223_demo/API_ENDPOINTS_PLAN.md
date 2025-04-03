# API Endpoints Access Control Plan

This document outlines which API endpoints are accessible for which user roles in the M223 Demo application.

## Public Endpoints (No Authentication Required)

- **GET /public** - Public content accessible to anyone
- **GET /** - Home endpoint, accessible to anyone
- **POST /api/auth/signin** - User login endpoint
- **POST /api/auth/signup** - User registration endpoint
- **GET /api/users** - List all users (typically this would be restricted, but for demo purposes it's public)
- **POST /api/users** - Create a new user (registration alternative)

## User Endpoints (ROLE_USER Required)

- **GET /private** - Private content only for authenticated users
- **Any endpoints under /private/** - Various private endpoints for regular users

## Admin Endpoints (ROLE_ADMIN Required)

- **GET /admin** - Admin panel home
- **Any endpoints under /admin/** - Various administrative endpoints

## Security Implementation

1. JWT-based authentication is implemented
2. JWT token is required in the Authorization header for protected endpoints
3. Roles are checked for authorization to specific endpoints
4. SecurityFilterChain is configured to enforce these restrictions

## How to Use the API

### Authentication

To access protected endpoints:

1. First, login using the `/api/auth/signin` endpoint with valid credentials
2. Receive a JWT token in the response
3. Include the token in the Authorization header of subsequent requests as:
   ```
   Authorization: Bearer <token>
   ```

### Registration

New users can register using the `/api/auth/signup` endpoint with:
- Username
- Email
- Password
- Optional roles (default is ROLE_USER if not specified)

### Role Assignment

- By default, new users get the ROLE_USER role
- ROLE_ADMIN can be assigned during registration if specified and the system allows it

## Implementation Notes

- JWT tokens expire after 24 hours (configurable in application.properties)
- The security is implemented with Spring Security and JWT
- User details are stored in MongoDB 