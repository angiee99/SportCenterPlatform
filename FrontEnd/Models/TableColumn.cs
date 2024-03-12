namespace SportCenter.Models;

public class TableColumn(
    string header,
    bool canFilter,
    bool canSort
    //F unc<IEnumerable<TableData>, IOrderedEnumerable<TableData>> sortFunction,
    // Func<TableData, bool> filterFunction
)
{
    public string Header { get; set; } = header;
    public bool CanFilter { get; set; } = canFilter;
    public bool CanSort { get; set; } = canSort;


    // Metoda pro řazení dat v tomto sloupci
    //public Func<IEnumerable<TableData>, IOrderedEnumerable<TableData>> SortFunction { get; set; } = sortFunction;

    // Metoda pro filtrování dat v tomto sloupci
    //public Func<TableData, bool> FilterFunction { get; set; } = filterFunction;
}