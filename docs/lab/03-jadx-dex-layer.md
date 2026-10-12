# Lab 03 — The Java Layer: DEX → smali → Java with jadx

**Goal:** decompile the Dalvik side of the game and learn to read decompiled
Java critically — including *why decompilers produce weird code*.

---

## 3.1 Run it

```bash
jadx -j 12 --no-debug-info -d out/jadx classes.dex classes2.dex
# INFO - progress: 3370 of 3370 (100%)
# ERROR - finished with errors, count: 8
```

**8 errors is normal.** Obfuscated SDK code, exotic control flow, and
verification-edge bytecode defeat the decompiler occasionally. Rule: *treat
decompiler errors as leads, not blockers.*

## 3.2 What came out (evidence)

5,205 `.java` files. Top packages (count of classes):

```
com/tapjoy 563    com/facebook 142    com/google 84    com/nexon 59
com/unity3d 39    com/adjust 29       com/naver 23     com/bumptech 13
twitter4j (whole Twitter client library, unused by game logic)
```

`kr/co/nexon` is the vendor auth SDK (**NPA — Nexon Platform Account**):
`kr.co.nexon.npaccount.NPAccount`, `NPAccountJNISupport` (JNI bridge the C#
side calls through), listeners for GCM push, banners, close events.

**Key insight to carry:** the *game logic never lives in Java*. Java here is a
services shell: auth, billing (`com.nexon.caocao.iab.CaoCaoIABActivity`),
push, ads. The C# side calls into it over JNI. So when rebuilding we can
**replace this whole shell** with modern equivalents — we do not need to port it.

## 3.3 Reading decompiled code critically

Decompilers translate bytecode → Java. Things you will see and must interpret:

| Artifact | Meaning |
|---|---|
| `Synthetic` methods, `access$000` | compiler-generated accessors for inner classes |
| `lambda$onCreate$0` | desugared lambda |
| `/* class */` oddities, `goto` remnants | exception/finally regions |
| string soup like `a1.a2.c` | obfuscated (ProGuard) SDK packages |

## 3.4 Exercises

- **3-A** Find `CaoCaoIABActivity` in the jadx output. List every Google
  Billing call it makes; map them to the modern Play Billing API equivalents.
- **3-B** Grep for `System.loadLibrary` — enumerate every `.so` the Java side
  loads, and match each to its ABI folder entry. (Expect `unity`, `il2cpp`,
  `ngsm`, `BlueDoveMediaRender`.)
- **3-C (JNI bridge)** Find how `NPAccountJNISupport` is invoked from Java and
  sketch the call direction: C# → ? → Java. We verify the C# side in Lab 04.

Solutions: `solutions/03.md`.
