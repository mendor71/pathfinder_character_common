create table character_attributes if not exists (
  ca_id bigserial
  , ca_character_id bigint
  , ca_attr_type varchar(255)
  , ca_value bigint
  , ca_modifier bigint
  , ca_temp_value_bonus bigint
  , ca_temp_modifier_bonus bigint
)
