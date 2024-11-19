local jwt_decoder = require "kong.plugins.jwt.jwt_parser"
local kong = kong

local JwtParserHandler = {
  PRIORITY = 1000,
  VERSION = "1.0.0",
}

function JwtParserHandler:access(conf)
  local auth_header = kong.request.get_header(conf.header_name)
  if not auth_header then
    return kong.response.exit(401, { message = "Unauthorized" })
  end

  local token = auth_header:match("Bearer%s+(.+)")
  if not token then
    return kong.response.exit(401, { message = "Unauthorized" })
  end

  local jwt, err = jwt_decoder:new(token)
  if err then
    return kong.response.exit(401, { message = "Invalid token" })
  end

  local claims = jwt.claims
  kong.service.request.set_header("X-User-Firstname", claims.firstname)
  kong.service.request.set_header("X-User-Lastname", claims.lastname)
  kong.service.request.set_header("X-User-Roles", table.concat(claims.roles, ","))
  kong.service.request.set_header("X-User-Email", claims.sub)
end

return JwtParserHandler