using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// ReSharper disable CheckNamespace
namespace Culinario_DB.EFCore.Tables;

[Table("RecipeCategory")]
public class RecipeCategory
{
    [Key] public int Id { get; set; }

    [MaxLength(20)] public required string Name { get; set; }
}