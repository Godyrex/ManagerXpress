export interface TableResponse {
  idTable: string;
  user: string;
  tableName: string;
  columns: {
    [key: string]: string
  };
  users: string[];
}
