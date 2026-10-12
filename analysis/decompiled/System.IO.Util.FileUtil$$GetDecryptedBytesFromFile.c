// name: System.IO.Util.FileUtil$$GetDecryptedBytesFromFile
// entry: 01b11e78

/* WARNING: Removing unreachable block (ram,0x01b12230) */
/* WARNING: Removing unreachable block (ram,0x01b121a0) */
/* WARNING: Removing unreachable block (ram,0x01b12110) */
/* WARNING: Removing unreachable block (ram,0x01b1207c) */
/* WARNING: Removing unreachable block (ram,0x01b12084) */
/* WARNING: Removing unreachable block (ram,0x01b12114) */
/* WARNING: Removing unreachable block (ram,0x01b121a4) */
/* WARNING: Removing unreachable block (ram,0x01b12234) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8
System_IO_Util_FileUtil__GetDecryptedBytesFromFile
          (undefined8 param_1,undefined8 param_2,undefined8 param_3,long param_4)

{
  long *plVar1;
  long *plVar2;
  long *plVar3;
  long *plVar4;
  undefined8 uVar5;
  undefined8 *puVar6;
  uint *puVar7;
  ulong uVar8;
  long lVar9;
  
  if ((bRam00000000049fbd28 & 1) == 0) {
    func_0x010c65d4(0x56cd);
    bRam00000000049fbd28 = 1;
  }
  if (param_4 == 0) {
    if (((*(byte *)(_class_System_Text_Encoding_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Text_Encoding_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    param_4 = System_Text_Encoding__get_ASCII(0,0);
  }
  plVar1 = (long *)func_0x010fdda8(_class_System_Security_Cryptography_DecryptedInfo_TypeInfo);
  System_Security_Cryptography_DecryptedInfo___ctor(plVar1,param_2,param_3);
  if (plVar1 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar9 = plVar1[5];
  plVar2 = (long *)func_0x010fdda8(_class_System_IO_MemoryStream_TypeInfo);
  System_IO_MemoryStream___ctor(plVar2,lVar9,0);
  lVar9 = plVar1[3];
  plVar3 = (long *)func_0x010fdda8(_class_System_Security_Cryptography_CryptoStream_TypeInfo);
  System_Security_Cryptography_CryptoStream___ctor(plVar3,plVar2,lVar9,0,0);
  plVar4 = (long *)func_0x010fdda8(_class_System_IO_BinaryReader_TypeInfo);
  System_IO_BinaryReader___ctor(plVar4,plVar3,param_4,0);
  uVar8 = plVar1[4];
  if (plVar4 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar5 = (**(code **)(*plVar4 + 0x210))(plVar4,uVar8 & 0xffffffff,*(undefined8 *)(*plVar4 + 0x218))
  ;
  lVar9 = *plVar4;
  if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
    uVar8 = 0;
    puVar7 = (uint *)(*(long *)(lVar9 + 0xb0) + 8);
    do {
      if (*(long *)(puVar7 + -2) == _class_System_IDisposable_TypeInfo) {
        puVar6 = (undefined8 *)(lVar9 + (ulong)*puVar7 * 0x10 + 0x130);
        goto code_r0x01b12068;
      }
      uVar8 = uVar8 + 1;
      puVar7 = puVar7 + 4;
    } while (uVar8 < *(ushort *)(lVar9 + 0x122));
  }
  puVar6 = (undefined8 *)func_0x010bc584(plVar4,_class_System_IDisposable_TypeInfo,0);
code_r0x01b12068:
  (*(code *)*puVar6)(plVar4,puVar6[1]);
  if (plVar3 != (long *)0x0) {
    lVar9 = *plVar3;
    if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
      uVar8 = 0;
      puVar7 = (uint *)(*(long *)(lVar9 + 0xb0) + 8);
      do {
        if (*(long *)(puVar7 + -2) == _class_System_IDisposable_TypeInfo) {
          puVar6 = (undefined8 *)(lVar9 + (ulong)*puVar7 * 0x10 + 0x130);
          goto code_r0x01b120fc;
        }
        uVar8 = uVar8 + 1;
        puVar7 = puVar7 + 4;
      } while (uVar8 < *(ushort *)(lVar9 + 0x122));
    }
    puVar6 = (undefined8 *)func_0x010bc584(plVar3,_class_System_IDisposable_TypeInfo,0);
code_r0x01b120fc:
    (*(code *)*puVar6)(plVar3,puVar6[1]);
  }
  if (plVar2 != (long *)0x0) {
    lVar9 = *plVar2;
    if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
      uVar8 = 0;
      puVar7 = (uint *)(*(long *)(lVar9 + 0xb0) + 8);
      do {
        if (*(long *)(puVar7 + -2) == _class_System_IDisposable_TypeInfo) {
          puVar6 = (undefined8 *)(lVar9 + (ulong)*puVar7 * 0x10 + 0x130);
          goto code_r0x01b1218c;
        }
        uVar8 = uVar8 + 1;
        puVar7 = puVar7 + 4;
      } while (uVar8 < *(ushort *)(lVar9 + 0x122));
    }
    puVar6 = (undefined8 *)func_0x010bc584(plVar2,_class_System_IDisposable_TypeInfo,0);
code_r0x01b1218c:
    (*(code *)*puVar6)(plVar2,puVar6[1]);
  }
  if (plVar1 != (long *)0x0) {
    lVar9 = *plVar1;
    if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
      uVar8 = 0;
      puVar7 = (uint *)(*(long *)(lVar9 + 0xb0) + 8);
      do {
        if (*(long *)(puVar7 + -2) == _class_System_IDisposable_TypeInfo) {
          puVar6 = (undefined8 *)(lVar9 + (ulong)*puVar7 * 0x10 + 0x130);
          goto code_r0x01b1221c;
        }
        uVar8 = uVar8 + 1;
        puVar7 = puVar7 + 4;
      } while (uVar8 < *(ushort *)(lVar9 + 0x122));
    }
    puVar6 = (undefined8 *)func_0x010bc584(plVar1,_class_System_IDisposable_TypeInfo,0);
code_r0x01b1221c:
    (*(code *)*puVar6)(plVar1,puVar6[1]);
  }
  return uVar5;
}


