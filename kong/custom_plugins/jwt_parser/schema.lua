local typedefs = require "kong.db.schema.typedefs"

return {
  name = "jwt_parser",
  fields = {
    { config = {
        type = "record",
        fields = {
          { header_name = typedefs.header_name { default = "Authorization" } },
        },
      },
    },
  },
}