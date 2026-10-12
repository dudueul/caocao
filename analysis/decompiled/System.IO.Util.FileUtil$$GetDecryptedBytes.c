// name: System.IO.Util.FileUtil$$GetDecryptedBytes
// entry: 01b122e8

/* WARNING: Removing unreachable block (ram,0x01b125f8) */
/* WARNING: Removing unreachable block (ram,0x01b12568) */
/* WARNING: Removing unreachable block (ram,0x01b124d4) */
/* WARNING: Removing unreachable block (ram,0x01b124dc) */
/* WARNING: Removing unreachable block (ram,0x01b1256c) */
/* WARNING: Removing unreachable block (ram,0x01b125fc) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8
System_IO_Util_FileUtil__GetDecryptedBytes
          (undefined8 param_1,undefined8 param_2,undefined8 param_3,long param_4)

{
  long *plVar1;
  long *plVar2;
  long *plVar3;
  undefined8 uVar4;
  undefined8 *puVar5;
  uint *puVar6;
  ulong uVar7;
  long lVar8;
  long lVar9;
  
  if ((bRam00000000049fbd29 & 1) == 0) {
    func_0x010c65d4(0x56ce);
    bRam00000000049fbd29 = 1;
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
    lVar8 = lRam0000000000000030;
    func_0x010eec64(0);
  }
  else {
    lVar8 = plVar1[6];
  }
  lVar9 = plVar1[3];
  plVar2 = (long *)func_0x010fdda8(_class_System_Security_Cryptography_CryptoStream_TypeInfo);
  System_Security_Cryptography_CryptoStream___ctor(plVar2,lVar8,lVar9,0,0);
  plVar3 = (long *)func_0x010fdda8(_class_System_IO_BinaryReader_TypeInfo);
  System_IO_BinaryReader___ctor(plVar3,plVar2,param_4,0);
  uVar7 = plVar1[4];
  if (plVar3 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar4 = (**(code **)(*plVar3 + 0x210))(plVar3,uVar7 & 0xffffffff,*(undefined8 *)(*plVar3 + 0x218))
  ;
  lVar8 = *plVar3;
  if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
    uVar7 = 0;
    puVar6 = (uint *)(*(long *)(lVar8 + 0xb0) + 8);
    do {
      if (*(long *)(puVar6 + -2) == _class_System_IDisposable_TypeInfo) {
        puVar5 = (undefined8 *)(lVar8 + (ulong)*puVar6 * 0x10 + 0x130);
        goto code_r0x01b124c0;
      }
      uVar7 = uVar7 + 1;
      puVar6 = puVar6 + 4;
    } while (uVar7 < *(ushort *)(lVar8 + 0x122));
  }
  puVar5 = (undefined8 *)func_0x010bc584(plVar3,_class_System_IDisposable_TypeInfo,0);
code_r0x01b124c0:
  (*(code *)*puVar5)(plVar3,puVar5[1]);
  if (plVar2 != (long *)0x0) {
    lVar8 = *plVar2;
    if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
      uVar7 = 0;
      puVar6 = (uint *)(*(long *)(lVar8 + 0xb0) + 8);
      do {
        if (*(long *)(puVar6 + -2) == _class_System_IDisposable_TypeInfo) {
          puVar5 = (undefined8 *)(lVar8 + (ulong)*puVar6 * 0x10 + 0x130);
          goto code_r0x01b12554;
        }
        uVar7 = uVar7 + 1;
        puVar6 = puVar6 + 4;
      } while (uVar7 < *(ushort *)(lVar8 + 0x122));
    }
    puVar5 = (undefined8 *)func_0x010bc584(plVar2,_class_System_IDisposable_TypeInfo,0);
code_r0x01b12554:
    (*(code *)*puVar5)(plVar2,puVar5[1]);
  }
  if (plVar1 != (long *)0x0) {
    lVar8 = *plVar1;
    if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
      uVar7 = 0;
      puVar6 = (uint *)(*(long *)(lVar8 + 0xb0) + 8);
      do {
        if (*(long *)(puVar6 + -2) == _class_System_IDisposable_TypeInfo) {
          puVar5 = (undefined8 *)(lVar8 + (ulong)*puVar6 * 0x10 + 0x130);
          goto code_r0x01b125e4;
        }
        uVar7 = uVar7 + 1;
        puVar6 = puVar6 + 4;
      } while (uVar7 < *(ushort *)(lVar8 + 0x122));
    }
    puVar5 = (undefined8 *)func_0x010bc584(plVar1,_class_System_IDisposable_TypeInfo,0);
code_r0x01b125e4:
    (*(code *)*puVar5)(plVar1,puVar5[1]);
  }
  return uVar4;
}


