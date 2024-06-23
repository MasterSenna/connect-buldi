-- V3__alter_mentor_certificado_column.sql
ALTER TABLE mentor ADD COLUMN certificado_temp oid;
-- O UPDATE pode precisar de uma lógica específica aqui para transferir os dados corretamente.
-- Por enquanto, vamos apenas adicionar a nova coluna.
-- Remova a coluna antiga e renomeie a nova coluna.
ALTER TABLE mentor DROP COLUMN certificado;
ALTER TABLE mentor RENAME COLUMN certificado_temp TO certificado;
