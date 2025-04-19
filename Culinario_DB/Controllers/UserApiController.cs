using System.Text.Json;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Culinario_DB.EFCore;
using Culinario_DB.EFCore.Models;
using Culinario_DB.EFCore.Tables;
using Culinario_DB.EFCore.Supporting_Classes;

namespace Culinario_DB.Controllers;

[Route("api/[controller]")]
[ApiController]
public class UserApiController(EfDataContext context) : ControllerBase
{
    private readonly DbHelper _dbHelper = new(context);

    // // GET api/GetUserById?id=
    // [HttpGet("GetUserById")]
    // public IActionResult GetUser(int id)
    // {
    //     var user = _dbHelper.GetUsers().FirstOrDefault(user => user!.Id == id, null);
    //
    //     return user == null ? NotFound() : Ok(JsonSerializer.Serialize(user));
    // }

    // Добавить для изображений

    // GET api/ValidateUser?hash=
    [HttpGet("ValidateUser")]
    public IActionResult ValidateUser(string hash)
    {
        throw new NotImplementedException();
    }

    // POST api/AddUser with JSON string in body: {json user}
    [HttpPost("AddUser")]
    public IActionResult Post([FromBody] string json)
    {
        try
        {
            var user = JsonSerializer.Deserialize<User>(json);

            if (user == null)
                return BadRequest("Invalid user data");

            var state = _dbHelper.AddUser(new UserModel(user));

            if (state != EntityState.Added)
                return BadRequest($"Error adding user with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error adding user with Exception Message: {ex.Message}");
        }
    }

    // PUT api/UpdateUser with JSON string in body: {json user}
    [HttpPut("UpdateUser")]
    public IActionResult Put([FromBody] string json)
    {
        try
        {
            var user = JsonSerializer.Deserialize<User>(json);

            if (user == null)
                return BadRequest("Invalid user data");

            var state = _dbHelper.UpdateUser(new UserModel(user));

            if (state != EntityState.Modified)
                return BadRequest($"Error updating user with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error updating user with Exception Message: {ex.Message}");
        }
    }

    // DELETE api/DeleteUser?id=&isDeleteRecipes=
    [HttpDelete("DeleteUser")]
    public IActionResult Delete(int id, bool isDeleteRecipes)
    {
        try
        {
            var state = _dbHelper.DeleteUser(id, isDeleteRecipes);

            if (state != EntityState.Deleted)
                return BadRequest($"Error removing user with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error removing user with Exception Message: {ex.Message}");
        }
    }
}