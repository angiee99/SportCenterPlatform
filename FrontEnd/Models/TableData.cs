using SportCenter.Enum;

namespace SportCenter.Models;

public class TableData(
    long id,
    string description,
    int capacity,
    DateTime dateTime,
    double antitude,
    double latitude,
    string location,
    SportType type
)
{
    public long Id { get; set; } = id;
    public string Description { get; set; } = description;
    public int Capacity { get; set; } = capacity;
    public DateTime DateTime { get; set; } = dateTime;
    public double Antitude { get; set; } = antitude;
    public double Latitude { get; set; } = latitude;
    public string Location { get; set; } = location;
    public SportType Type { get; set; } = type;
}