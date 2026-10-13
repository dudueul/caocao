// name: FairRuleManager$$GetDefaultFairRule
// entry: 017498c4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FairRuleManager__GetDefaultFairRule(long param_1,undefined4 param_2)

{
  undefined4 uVar1;
  uint uVar2;
  uint uVar3;
  int iVar4;
  uint uVar5;
  undefined8 uVar6;
  undefined8 *puVar7;
  int *piVar8;
  uint *puVar9;
  ulong uVar10;
  long lVar11;
  long lVar12;
  long *plVar13;
  long lVar14;
  long lVar15;
  long unaff_x25;
  uint unaff_w27;
  
  if ((bRam00000000049f9c1e & 1) == 0) {
    func_0x010c65d4(0x566c);
    bRam00000000049f9c1e = 1;
  }
  lVar11 = *(long *)(param_1 + 0x38);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                    (lVar11,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_ContainsKey__);
  if ((uVar5 & 1) == 0) {
    lVar12 = *(long *)(param_1 + 0x38);
    lVar11 = func_0x010fdda8(_class_FairRule_TypeInfo);
    System_Object___ctor(lVar11,0);
    if (lVar11 == 0) {
      func_0x010eec64(0);
      uRam0000000000000010 = param_2;
      func_0x010eec64(0);
    }
    else {
      *(undefined4 *)(lVar11 + 0x10) = param_2;
    }
    *(undefined4 *)(lVar11 + 0x14) = 0xc;
    if (((*(byte *)(_class_ClientConst_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_ClientConst_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar14 = *(long *)(*(long *)(_class_ClientConst_TypeInfo + 0xb8) + 0x1c0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
      func_0x010eec64(0);
    }
    lVar15 = (*(long *)(lVar14 + 0x18) << 0x20) + -0x100000000;
    if ((uint)*(long *)(lVar14 + 0x18) <= (uint)((ulong)lVar15 >> 0x20)) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    uVar1 = *(undefined4 *)(lVar14 + (lVar15 >> 0x20) * 4 + 0x20);
    if (lVar11 == 0) {
      func_0x010eec64(0);
      uRam0000000000000018 = uVar1;
      func_0x010eec64(0);
      uRam000000000000001c = 1;
      func_0x010eec64(0);
      uRam000000000000001d = 1;
      func_0x010eec64(0);
    }
    else {
      *(undefined4 *)(lVar11 + 0x18) = uVar1;
      *(undefined2 *)(lVar11 + 0x1c) = 0x101;
    }
    *(undefined4 *)(lVar11 + 0x20) = 5;
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(lVar11 + 0x28) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Add
              (lVar12,param_2,lVar11,
               _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_Add__);
  }
  lVar11 = *(long *)(param_1 + 0x38);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  lVar12 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_get_Item__;
  if ((bRam0000000004a082c7 & 1) == 0) {
    func_0x010c65d4(0x40f3);
    bRam0000000004a082c7 = 1;
  }
  plVar13 = *(long **)(lVar11 + 0x40);
  if (plVar13 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar15 = *(long *)(*(long *)(*(long *)(lVar12 + 0x18) + 0xc0) + 0x118);
  func_0x010bbb2c(lVar15);
  lVar14 = *plVar13;
  if ((ulong)*(ushort *)(lVar14 + 0x122) != 0) {
    uVar10 = 0;
    piVar8 = (int *)(*(long *)(lVar14 + 0xb0) + 8);
    do {
      if (*(long *)(piVar8 + -2) == lVar15) {
        puVar7 = (undefined8 *)(lVar14 + (ulong)(*piVar8 + 1) * 0x10 + 0x130);
        goto code_r0x03188408;
      }
      uVar10 = uVar10 + 1;
      piVar8 = piVar8 + 4;
    } while (uVar10 < *(ushort *)(lVar14 + 0x122));
  }
  puVar7 = (undefined8 *)func_0x010bc584(plVar13,lVar15,1);
code_r0x03188408:
  uVar5 = (*(code *)*puVar7)(plVar13,param_2,puVar7[1]);
  lVar14 = *(long *)(lVar11 + 0x10);
  if (lVar14 == 0) {
    func_0x010eec64(0);
    func_0x010eec64(0);
  }
  uVar2 = *(uint *)(lVar14 + 0x18);
  iVar4 = 0;
  if (uVar2 != 0) {
    iVar4 = (int)(uVar5 & 0x7fffffff) / (int)uVar2;
  }
  uVar3 = (uVar5 & 0x7fffffff) - iVar4 * uVar2;
  if (uVar2 <= uVar3) {
    uVar6 = func_0x010ef850();
    func_0x010eebec(uVar6,0,0);
  }
  iVar4 = *(int *)(lVar14 + (ulong)uVar3 * 4 + 0x20);
  if (iVar4 != 0) {
    unaff_w27 = iVar4 - 1;
    do {
      lVar14 = *(long *)(lVar11 + 0x18);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      unaff_x25 = (long)(int)unaff_w27;
      if (*(uint *)(lVar14 + 0x18) <= unaff_w27) {
        uVar6 = func_0x010ef850();
        func_0x010eebec(uVar6,0,0);
      }
      if (*(uint *)(lVar14 + unaff_x25 * 8 + 0x20) == (uVar5 | 0x80000000)) {
        plVar13 = *(long **)(lVar11 + 0x40);
        lVar14 = *(long *)(lVar11 + 0x20);
        if (lVar14 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar14 + 0x18) <= unaff_w27) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        uVar1 = *(undefined4 *)(lVar14 + unaff_x25 * 4 + 0x20);
        if (plVar13 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar15 = *(long *)(*(long *)(*(long *)(lVar12 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar15);
        lVar14 = *plVar13;
        if ((ulong)*(ushort *)(lVar14 + 0x122) != 0) {
          uVar10 = 0;
          puVar9 = (uint *)(*(long *)(lVar14 + 0xb0) + 8);
          do {
            if (*(long *)(puVar9 + -2) == lVar15) {
              puVar7 = (undefined8 *)(lVar14 + (ulong)*puVar9 * 0x10 + 0x130);
              goto code_r0x0318855c;
            }
            uVar10 = uVar10 + 1;
            puVar9 = puVar9 + 4;
          } while (uVar10 < *(ushort *)(lVar14 + 0x122));
        }
        puVar7 = (undefined8 *)func_0x010bc584(plVar13,lVar15,0);
code_r0x0318855c:
        uVar10 = (*(code *)*puVar7)(plVar13,uVar1,param_2,puVar7[1]);
        if ((uVar10 & 1) != 0) goto code_r0x031885e8;
      }
      lVar14 = *(long *)(lVar11 + 0x18);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar14 + 0x18) <= unaff_w27) {
        uVar6 = func_0x010ef850();
        func_0x010eebec(uVar6,0,0);
      }
      unaff_w27 = *(uint *)(lVar14 + unaff_x25 * 8 + 0x24);
    } while (unaff_w27 != 0xffffffff);
  }
  uVar6 = func_0x010fdda8(_class_System_Collections_Generic_KeyNotFoundException_TypeInfo);
  System_Collections_Generic_KeyNotFoundException___ctor(uVar6,0);
  func_0x010eebec(uVar6,0,_mm_Method_System_Collections_Generic_Dictionary<int,_object>_get_Item__);
  func_0x0112148c();
code_r0x031885e8:
  lVar11 = *(long *)(lVar11 + 0x28);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar11 + 0x18) <= unaff_w27) {
    uVar6 = func_0x010ef850();
    func_0x010eebec(uVar6,0,0);
  }
  return *(undefined8 *)(lVar11 + unaff_x25 * 8 + 0x20);
}


