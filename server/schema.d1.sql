-- D1 schema — players/progress (Milestone 2 scope).
-- [RE] the original schema is unrecoverable from the client; this is the
-- rebuilt contract driven by the recovered payloads (protocol_events.md).

CREATE TABLE IF NOT EXISTS players (
  user_id    TEXT PRIMARY KEY,
  auth_key   TEXT NOT NULL,
  created_at INTEGER NOT NULL,
  last_seen  INTEGER
);

CREATE TABLE IF NOT EXISTS inventory (
  user_id  TEXT NOT NULL,
  item_id  INTEGER NOT NULL,
  count    INTEGER NOT NULL DEFAULT 1,
  tier     INTEGER,
  PRIMARY KEY (user_id, item_id)
);

CREATE TABLE IF NOT EXISTS progress (
  user_id  TEXT NOT NULL,
  key      TEXT NOT NULL,        -- e.g. "scenario:3:clear", "tutorial:step"
  value    TEXT NOT NULL,
  updated_at INTEGER NOT NULL,
  PRIMARY KEY (user_id, key)
);

-- [RE] Item catalog — schema mirrors the client's Item asset fields
-- (Item.cs stub: 49 fields). CONTENT is author-supplied (the original
-- catalog lived server-side and is unrecoverable; schema is the contract).
CREATE TABLE IF NOT EXISTS items (
  item_id INTEGER PRIMARY KEY,
  name    TEXT NOT NULL,
  tier    INTEGER NOT NULL DEFAULT 0,
  slot    INTEGER NOT NULL DEFAULT 0,
  item_type INTEGER NOT NULL DEFAULT 0,
  box_type INTEGER NOT NULL DEFAULT 0,
  price   INTEGER NOT NULL DEFAULT 0,   -- cash price (payType)
  gold    INTEGER NOT NULL DEFAULT 0,   -- gold price
  item_effect INTEGER NOT NULL DEFAULT 0,
  effect_value INTEGER NOT NULL DEFAULT 0,
  max_value INTEGER NOT NULL DEFAULT 0,
  type_value INTEGER NOT NULL DEFAULT 0,
  pay_type INTEGER NOT NULL DEFAULT 0,
  atk INTEGER DEFAULT 0, atk_up INTEGER DEFAULT 0,
  def INTEGER DEFAULT 0, def_up INTEGER DEFAULT 0,
  inte INTEGER DEFAULT 0, inte_up INTEGER DEFAULT 0,
  agi INTEGER DEFAULT 0, agi_up INTEGER DEFAULT 0,
  mor INTEGER DEFAULT 0, mor_up INTEGER DEFAULT 0,
  hp INTEGER DEFAULT 0, hp_up INTEGER DEFAULT 0,
  mp INTEGER DEFAULT 0, mp_up INTEGER DEFAULT 0,
  max_lv INTEGER NOT NULL DEFAULT 1,
  target_type INTEGER NOT NULL DEFAULT 0,
  skill_id INTEGER NOT NULL DEFAULT 0,
  unit_id INTEGER NOT NULL DEFAULT 0,
  atlas TEXT DEFAULT '', icon TEXT DEFAULT '',
  country INTEGER NOT NULL DEFAULT 0,
  job_type INTEGER NOT NULL DEFAULT 0
);

-- [RE] player inventory — owned instances
CREATE TABLE IF NOT EXISTS inventory (
  user_id TEXT NOT NULL,
  item_id INTEGER NOT NULL,
  count INTEGER NOT NULL DEFAULT 1,
  tier INTEGER NOT NULL DEFAULT 0,        -- instance tier (enhance level)
  locked INTEGER NOT NULL DEFAULT 0,
  acquired_at INTEGER NOT NULL,
  PRIMARY KEY (user_id, item_id)
);

-- [RE] soft currency
CREATE TABLE IF NOT EXISTS wallets (
  user_id TEXT PRIMARY KEY,
  gold INTEGER NOT NULL DEFAULT 0,
  cash INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS battles (
  battle_id TEXT PRIMARY KEY,
  user_id TEXT NOT NULL,
  kind TEXT NOT NULL,
  ref_id TEXT,
  state TEXT NOT NULL,
  is_win INTEGER, grade INTEGER, occupation_pct INTEGER, play_time_ms INTEGER,
  opened_at INTEGER NOT NULL, closed_at INTEGER
);

-- [RE] guild core (schema from /guild/* endpoints + GuildData usage)
CREATE TABLE IF NOT EXISTS guilds (
  guild_id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL UNIQUE,
  motd TEXT NOT NULL DEFAULT '',
  emblem INTEGER NOT NULL DEFAULT 0,
  join_config INTEGER NOT NULL DEFAULT 0,   -- [RE] set_join_config
  created_at INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS guild_members (
  guild_id INTEGER NOT NULL,
  user_id TEXT NOT NULL,
  job INTEGER NOT NULL DEFAULT 0,            -- [RE] change_member_job (grade)
  joined_at INTEGER NOT NULL,
  PRIMARY KEY (guild_id, user_id)
);

CREATE TABLE IF NOT EXISTS guild_applicants (
  guild_id INTEGER NOT NULL,
  user_id TEXT NOT NULL,
  applied_at INTEGER NOT NULL,
  PRIMARY KEY (guild_id, user_id)
);

-- [RE] guild-war world state — the FlatBuffers snapshot rows.
-- layout mirrors key_corps/key_building struct fields (dsa-02 §1b).
CREATE TABLE IF NOT EXISTS world_corps (
  world_id INTEGER NOT NULL,
  corps_id INTEGER NOT NULL,
  guild_id INTEGER NOT NULL,
  pos_x INTEGER NOT NULL,
  pos_y INTEGER NOT NULL,
  face_unit_id INTEGER NOT NULL DEFAULT 0,
  job_type INTEGER NOT NULL DEFAULT 0,
  military_power INTEGER NOT NULL DEFAULT 0,
  updated_seq INTEGER NOT NULL,
  PRIMARY KEY (world_id, corps_id)
);

CREATE TABLE IF NOT EXISTS world_buildings (
  world_id INTEGER NOT NULL,
  place_id INTEGER NOT NULL,
  kind INTEGER NOT NULL DEFAULT 0,
  guild_id INTEGER NOT NULL DEFAULT 0,
  updated_seq INTEGER NOT NULL,
  PRIMARY KEY (world_id, place_id)
);

-- [RE] world sequence counter (the from_seq/to_seq sync protocol)
CREATE TABLE IF NOT EXISTS world_seq (
  world_id INTEGER PRIMARY KEY,
  seq INTEGER NOT NULL DEFAULT 0
);
