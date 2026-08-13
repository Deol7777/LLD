package Abstraction;

import java.util.List;

abstract class DataExporter {

    boolean validate(List<String> data) {
        if (data == null || data.size() == 0) {
            System.out.println("Export failed: No data to export.");
            return false;
        }
        System.out.println("Validation passed. Exporting " + data.size() + " records.");
        return true;
    }

    abstract void export(List<String> data);

}

class CSVExporter extends DataExporter {

    @Override
    void export(List<String> data) {
        if (!validate(data))
            return;
        System.out.println("CSV: " + String.join(",", data));
        return;
    }

}

class JSONExporter extends DataExporter {
    @Override
    void export(List<String> data) {
        if (!validate(data))
            return;
        StringBuilder sb = new StringBuilder("JSON: [");
        for (int i = 0; i < data.size(); i++) {
            sb.append("\"").append(data.get(i)).append("\"");
            if (i < data.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
        return;
    }
}
