namespace Culinario_DB.EFCore.Models;

public interface IModel<out TModel>
{
    //Key - Ключ таблицы, Required - обязательное поле(not null)
    /// <summary>
    /// Идентификатор пользователя
    /// </summary>
    public int Id { get; set; }

    public TModel ToEntity();
}