using Culinario_DB.EFCore.Tables;
using Microsoft.EntityFrameworkCore;
using Models = Culinario_DB.EFCore.Models;

namespace Culinario_DB.EFCore.Supporting_Classes;

public partial class DbHelper
{
    private readonly EfDataContext _context;

    public DbHelper(EfDataContext context)
    {
        _context = context;
    }

    public static EntityState UpdateRangeObj<T, T1>(List<T>? objModelList, List<T1>? tableModelList,
        Func<T, bool, EntityState> addFunction,
        Func<T, bool, bool, EntityState> updateFunction,
        Func<int, bool, EntityState> deleteFunction)
        where T : Models.IModel<T1>
        where T1 : Tables.ITable
    {
        if (objModelList != null)
        {
            if (tableModelList != null)
            {
                objModelList.ForEach(model => updateFunction(model, true, false));

                foreach (var model in tableModelList.Where(oldModel =>
                             objModelList.All(newModel => newModel.Id != oldModel.Id)))
                    deleteFunction(model.Id, false);
            }
            else
                objModelList.ForEach(step => addFunction(step, false));
        }
        else
            tableModelList?.ForEach(step => deleteFunction(step.Id, false));
        
        return EntityState.Unchanged; // переделать
    }
    
    public static EntityState UpdateObj<T, T1>(T? objModel, T1? tableModel,
        Func<T, bool, EntityState> addFunction,
        Func<T, bool, bool, EntityState> updateFunction,
        Func<int, bool, EntityState> deleteFunction)
        where T : Models.IModel<T1>
        where T1 : Tables.ITable
    {
        if (objModel != null)
        {
            if (tableModel != null)
                updateFunction(objModel,false, false);
            else
                addFunction(objModel, false);
        }
        else if (tableModel != null)
            deleteFunction(tableModel.Id, false);
        
        return EntityState.Unchanged; // переделать
    }
}