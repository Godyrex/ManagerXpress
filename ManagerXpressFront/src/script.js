function addNewColumn() {
  const columnContainer = document.getElementById("columnContainer");

  // Create new column div
  const newColumn = document.createElement("div");
  newColumn.className = "column";

  // Create new column name label and input
  const columnNameLabel = document.createElement("label");
  columnNameLabel.textContent = "Column Name:";
  const columnNameInput = document.createElement("input");
  columnNameInput.type = "text";
  columnNameInput.className = "columnInput";
  columnNameInput.name = "columnName";
  columnNameInput.required = true;

  // Create new column type label and input
  const columnTypeLabel = document.createElement("label");
  columnTypeLabel.textContent = "Column Type:";
  const columnTypeInput = document.createElement("input");
  columnTypeInput.type = "text";
  columnTypeInput.className = "columnInput";
  columnTypeInput.name = "columnType";
  columnTypeInput.required = true;

  // Append new elements to the column div
  newColumn.appendChild(columnNameLabel);
  newColumn.appendChild(columnNameInput);
  newColumn.appendChild(columnTypeLabel);
  newColumn.appendChild(columnTypeInput);

  // Append the new column div to the container
  columnContainer.appendChild(newColumn);
}
