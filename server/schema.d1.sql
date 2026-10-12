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
