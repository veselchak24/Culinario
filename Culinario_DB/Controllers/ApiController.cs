using System.Text.Json;
using Culinario_DB.EFCore;
using Culinario_DB.EFCore.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Culinario_DB.Controllers;

[Route("api/[controller]")]
[ApiController]
public class ApiController(EfDataContext context) : ControllerBase
{
    private readonly DbHelper _dbHelper = new(context);

    // GET api/GetUserById?id=5
    [HttpGet("GetUserById")]
    public IActionResult GetUser(int id)
    {
        var user = _dbHelper.GetUsers().FirstOrDefault(user => user!.Id == id, null);

        return user == null ? NotFound() : Ok(JsonSerializer.Serialize(user));
    }

    // POST api/AddUser with JSON string in body: {json user}
    [HttpPost("AddUser")]
    public IActionResult Post([FromBody] string json)
    {
        try
        {
            var user = JsonSerializer.Deserialize<UserModel>(json);

            if (user == null)
                return BadRequest("Invalid user data");

            var state = _dbHelper.AddUser(user);

            if (state != EntityState.Added)
                return BadRequest($"Error adding user with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error adding user with Exception Message: {ex.Message}");
        }
    }

    // PUT apiUpdateUser with JSON string in body: {json user}
    [HttpPut("UpdateUser")]
    public IActionResult Put([FromBody] string json)
    {
        try
        {
            var user = JsonSerializer.Deserialize<UserModel>(json);

            if (user == null)
                return BadRequest("Invalid user data");

            var state = _dbHelper.UpdateUser(user);

            if (state != EntityState.Modified)
                return BadRequest($"Error updating user with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error updating user with Exception Message: {ex.Message}");
        }
    }

    // DELETE api/DeleteUser?id=5
    [HttpDelete("DeleteUser")]
    public IActionResult Delete(int id)
    {
        try
        {
            var state = _dbHelper.DeleteUser(id);

            if (state != EntityState.Deleted)
                return BadRequest($"Error adding user with EntityState Code: {state}");

            return Ok();
        }
        catch (Exception ex)
        {
            return BadRequest($"Error adding user with Exception Message: {ex.Message}");
        }
    }
}