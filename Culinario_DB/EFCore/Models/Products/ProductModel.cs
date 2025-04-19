namespace Culinario_DB.EFCore.Models;

public class ProductModel : IModel<Tables.Product>
{
    /// <summary>
    /// Идентификатор продукта
    /// </summary>
    public int Id { get; set; }

    /// <summary>
    /// Название продукта
    /// </summary>
    public string Name { get; set; }

    /// <summary>
    /// Описание продукта
    /// </summary>
    public string Description { get; set; }

    /// <summary>
    /// Пищевая ценность продукта
    /// </summary>
    public string Nutritional { get; set; }

    /// <summary>
    /// Категория продукта
    /// </summary>
    public ProductCategoryModel CategoryId { get; set; }

    public ProductModel(Tables.Product product)
    {
        Id = product.Id;
        Name = product.Name;
        Description = product.Description;
        Nutritional = product.Nutritional;
        CategoryId = new ProductCategoryModel(product.CategoryId);
    }

    public Tables.Product ToEntity()
    {
        return new Tables.Product()
        {
            Id = Id,
            Name = Name,
            Description = Description,
            Nutritional = Nutritional,
            CategoryId = CategoryId.ToEntity()
        };
    }
}