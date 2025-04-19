using System.Text.Json;
using Microsoft.AspNetCore.Mvc;
using Culinario_DB.EFCore;
using Culinario_DB.EFCore.Models;
using Culinario_DB.EFCore.Tables;
using Culinario_DB.EFCore.Supporting_Classes;
using Microsoft.EntityFrameworkCore;

namespace Culinario_DB.Controllers;

[Route("api/[controller]")]
[ApiController]
public class RecipeApiController(EfDataContext context) : ControllerBase
{
    private readonly DbHelper _dbHelper = new(context);

    // GET api/GetRecipeById?userId=
    [HttpGet("GetRecipeById")]
    public IActionResult GetRecipeById(int recipeId)
    {
        var recipeModel = _dbHelper.GetRecipe(recipeId);

        return recipeModel != null ? Ok(JsonSerializer.Serialize(recipeModel.ToEntity())) : NotFound();
    }

    // POST api/AddRecipe?userId= with JSON string in body: {json recipe}
    [HttpPost("AddRecipe")]
    public IActionResult PostAddRecipe(int userId, [FromBody] string json)
    {
        try
        {
            var recipe = JsonSerializer.Deserialize<Recipe>(json);

            if (recipe == null)
                return BadRequest("Invalid recipe data");

            var state = _dbHelper.AddRecipe(new RecipeModel(recipe));

            if (state != EntityState.Added)
                return BadRequest($"Error adding recipe with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error adding user with Exception Message: {ex.Message}");
        }
    }

    // PUT api/UpdateRecipe?userId= with JSON string in body: {json recipe}
    [HttpPut("UpdateRecipe")]
    public IActionResult PutUpdateRecipe([FromBody] string json)
    {
        try
        {
            var user = JsonSerializer.Deserialize<Recipe>(json);

            if (user == null)
                return BadRequest("Invalid user data");

            var state = _dbHelper.UpdateRecipe(new RecipeModel(user));

            if (state != EntityState.Modified)
                return BadRequest($"Error updating recipe with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error updating recipe with Exception Message: {ex.Message}");
        }
    }

    // DELETE api/DeleteRecipe?id= with JSON string in body: {json recipe}
    [HttpDelete("DeleteRecipe")]
    public IActionResult DeleteRecipe(int id, [FromBody] string json)
    {
        try
        {
            var state = _dbHelper.DeleteRecipe(id);

            if (state != EntityState.Deleted)
                return BadRequest($"Error removing recipe with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error removing recipe with Exception Message: {ex.Message}");
        }
    }
}