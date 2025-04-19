using System.ComponentModel.DataAnnotations.Schema;

// ReSharper disable CheckNamespace
namespace Culinario_DB.EFCore.Tables;

[Table("RecipeImage")]
public class RecipeImage : Image
{
    public required Recipe Recipe { get; set; }
}