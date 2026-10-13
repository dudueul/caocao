// brotli-file.mjs — root-free brotli via Node's built-in zlib (Lab 09).
// Usage: brotli-file.mjs <in> <out.br>
import { brotliCompressSync } from "node:zlib";
import { readFileSync, writeFileSync } from "node:fs";
const [inPath, outPath] = process.argv.slice(2);
writeFileSync(outPath, brotliCompressSync(readFileSync(inPath), { params: {
  [0x0b]: 9,        // quality = 9
  [0x08]: 22,       // lgwin
}}));
console.log(outPath);
