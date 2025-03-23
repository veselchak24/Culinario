using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Culinario_DB.EFCore.Tables;

[Table("Nutritional")]
public class Nutritional
{
    [Key] public int Id { get; set; }

    public required int Calories { get; set; }

    /// <summary>
    /// Белки
    /// </summary>
    public int Protein { get; set; }

    /// <summary>
    /// Жиры
    /// </summary>
    public int Fat { get; set; }

    /// <summary>
    /// Углеводы
    /// </summary>
    public int Carbohydrates { get; set; }
}