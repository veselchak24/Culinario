using Microsoft.EntityFrameworkCore;

namespace Culinario_DB.EFCore.Supporting_Classes;

public class ResponceState
{
    public EntityState State { get; init; }
    public string? Message { get; init; }
    public DateTime? Date { get; init; }
    public List<ResponceState>? SubStates { get; set; }
}