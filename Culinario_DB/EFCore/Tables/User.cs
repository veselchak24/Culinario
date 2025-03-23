using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Culinario_DB.EFCore.Tables;

[Table("User")]
public class User
{
    //Key - Ключ таблицы, Required - обязательное поле(not null)
    /// <summary>
    /// Идентификатор пользователя
    /// </summary>
    [Key]
    public int Id { get; set; }

    /// <summary>
    /// Имя пользователя
    /// </summary>
    [MaxLength(21)]
    [Required]
    public string Name { get; set; } = string.Empty;

    /// <summary>
    /// Email пользователя
    /// </summary>
    [MaxLength(31)]
    public string? Email { get; set; }

    /// <summary>
    /// Аватар пользователя
    /// </summary>
    public UserImage? UserImage { get; set; }

    /// <summary>
    /// Рецепты пользователя
    /// </summary>
    public List<Recipe>? Recipes { get; set; }

    /// <summary>
    /// Избранные рецепты пользователя
    /// </summary>
    public List<Recipe>? FavouriteRecipes { get; set; }
}