export interface TableRequest {
  tableName: string;
  columns: { [key: string]: string };
}
