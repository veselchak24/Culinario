using System.ComponentModel.DataAnnotations;

namespace Culinario_DB.EFCore.Tables;

public interface ITable
{
    [Key] public int Id { get; set; }
}