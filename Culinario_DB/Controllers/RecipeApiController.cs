using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Culinario_DB.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RecipeApiController : ControllerBase
    {
        // API Recipes

        // GET api/GetRecipeById?userId=
        [HttpGet("GetRecipeById")]
        public IActionResult GetRecipeById(int userId, int recipeId)
        {
            throw new NotImplementedException();
        }

        // POST api/AddRecipe?userId= with JSON string in body: {json recipe}
        [HttpPost("AddRecipe")]
        public IActionResult AddRecipe(int userId, [FromBody] string json)
        {
            throw new NotImplementedException();
        }

        // PUT api/UpdateRecipe?userId= with JSON string in body: {json recipe}
        [HttpPut("UpdateRecipe")]
        public IActionResult UpdateRecipe(int userId, [FromBody] string json)
        {
            throw new NotImplementedException();
        }

        // DELETE api/DeleteRecipe?userId= with JSON string in body: {json recipe}
        [HttpDelete("DeleteRecipe")]
        public IActionResult DeleteRecipe(int userId, [FromBody] string json)
        {
            throw new NotImplementedException();
        }
    }
}
