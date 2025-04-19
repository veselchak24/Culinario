using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// ReSharper disable CheckNamespace
namespace Culinario_DB.EFCore.Tables;

[Table("RecipeSteps")]
public class RecipeSteps : ITable
{
    [Key] public int Id { get; set; }
    public required Recipe Recipe { get; set; }
    [MaxLength(200)] public required string Description { get; set; }
    public required List<RecipeImage>? Images { get; set; }
}