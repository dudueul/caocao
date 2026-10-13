import { brotliCompressSync } from "node:zlib";
import { readFileSync } from "node:fs";
process.stdout.write(String(brotliCompressSync(readFileSync(process.argv[2])).length));
