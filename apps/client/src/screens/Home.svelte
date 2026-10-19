<script lang="ts">
import { cleanName, normalizeRoomCode, ROOM_CODE, roomCodeFrom } from "@tk/protocol";
import { markCreating, savedName, saveName } from "../lib/identity.ts";
import { T } from "../lib/text.ts";

/** Builds made without a server (a static preview) hide the Arena. */
const hasServer = import.meta.env.VITE_NO_SERVER !== "1";
let name = $state(savedName());
let code = $state("");
const validName = $derived(cleanName(name));
const validCode = $derived(ROOM_CODE.test(normalizeRoomCode(code)));

function remember() {
  if (validName) saveName(validName);
}

function create() {
  remember();
  const room = roomCodeFrom(crypto.getRandomValues(new Uint8Array(6)));
  markCreating(room);
  location.hash = `#/r/${room}`;
}

function join() {
  remember();
  location.hash = `#/r/${normalizeRoomCode(code)}`;
}
</script>

<main class="home">
  <header>
    <a class="back" href="#/">← 타이틀</a>
    <h1>{T.arena}</h1>
    <p class="muted">친구와 겨루는 실시간 대전</p>
  </header>

  {#if hasServer}
    <section class="card">
      <h2>대전 방</h2>
      <label for="name">{T.yourName}</label>
      <input id="name" bind:value={name} maxlength="12" placeholder="조조팬" onchange={remember}>
      <button type="button" class="primary" disabled={!validName} onclick={create}>
        {T.createRoom}
      </button>
      <div class="row">
        <input
          id="code"
          bind:value={code}
          maxlength="9"
          placeholder={T.roomCode}
          autocapitalize="characters"
          onkeydown={(e) => e.key === "Enter" && validName && validCode && join()}
        >
        <button type="button" disabled={!validName || !validCode} onclick={join}>
          {T.joinRoom}
        </button>
      </div>
      <p class="muted small">
        친구 둘이 장수 6명씩 번갈아 고른 뒤 겨룹니다. 행동마다 45초, 예비 시간 3분.
      </p>
    </section>
  {/if}

  <section class="card">
    <h2>{T.sandbox}</h2>
    <p class="muted small">오소 습격: 조조군을 이끌고 AI와 싸웁니다.</p>
    <div class="row">
      <a class="button primary" href="#/sandbox">{T.sandbox}</a>
      <a class="button" href="#/demo">{T.watchDemo}</a>
    </div>
  </section>
</main>

<style>
.home {
  min-height: 100%;
  overflow-y: auto;
  display: grid;
  align-content: start;
  justify-items: center;
  gap: 16px;
  padding-block: 32px;
  padding-left: max(16px, env(safe-area-inset-left, 0px));
  padding-right: max(16px, env(safe-area-inset-right, 0px));
}
header {
  position: relative;
  width: min(420px, 100%);
  text-align: center;
}
.back {
  position: absolute;
  left: 0;
  top: 8px;
  font-size: 13px;
  color: var(--muted);
  text-decoration: none;
}
h1 {
  margin: 0;
  font-size: 40px;
  letter-spacing: 0.08em;
  color: var(--accent);
}
h2 {
  margin: 0;
  font-size: 18px;
}
.card {
  width: min(420px, 100%);
  display: grid;
  gap: 10px;
  padding: 16px;
  background: var(--panel);
  border: 1px solid var(--line);
  border-radius: 10px;
}
.row {
  display: flex;
  gap: 8px;
}
.row input {
  flex: 1;
  min-width: 0;
  text-transform: uppercase;
}
.small {
  margin: 0;
  font-size: 12px;
}
label {
  font-size: 12px;
  color: var(--muted);
}
</style>
