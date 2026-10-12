// schema.ts — zod validation for the recovered wire protocol.
// [RE] Event catalog: analysis/protocol_events.md (decompiled call sites).
import { z } from "zod";

export const LoginPayload = z.object({
  userId: z.number().int().nonnegative().or(z.string()),
  authKey: z.string().min(1).max(256),
});
export type LoginPayload = z.infer<typeof LoginPayload>;

export const EnterWorldPayload = z.object({
  world_id: z.number().int().nonnegative().optional(),
  guild_id: z.number().int().nonnegative().optional(),
  room_type: z.string().max(64).optional(),
});
export type EnterWorldPayload = z.infer<typeof EnterWorldPayload>;

export const WantRoomPayload = z.object({
  room_type: z.string().max(64),
});
export type WantRoomPayload = z.infer<typeof WantRoomPayload>;

export const BroadcastPayload = z.object({
  room_id: z.string().max(128).optional(),
  type: z.number().int().optional(),          // ChattingMessageType
  sub_type: z.number().int().optional(),      // ChattingSubMsgType
  msg: z.string().max(2000),
});

export const RestArgs = z.array(z.unknown()).min(1); // [user_id, ...args]

export const GuildChannelEvents = [
  "_guild_create", "_guild_get", "_guild_leave", "_guild_break", "_guild_apply",
] as const;
