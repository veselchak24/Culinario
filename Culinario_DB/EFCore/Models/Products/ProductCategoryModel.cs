namespace Culinario_DB.EFCore.Models;

public class ProductCategoryModel(Tables.ProductCategory productCategory) : IModel<Tables.ProductCategory>
{
    public int Id { get; set; } = productCategory.Id;

    public string Name { get; set; } = productCategory.Name;

    public byte[] Image { get; set; } = productCategory.Image;

    public Tables.ProductCategory ToEntity()
    {
        return new Tables.ProductCategory
        {
            Id = Id,
            Name = Name,
            Image = Image
        };
    }
}