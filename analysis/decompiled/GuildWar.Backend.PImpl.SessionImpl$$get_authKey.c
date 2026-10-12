// name: GuildWar.Backend.PImpl.SessionImpl$$get_authKey
// entry: 01b30b48

/* WARNING: Removing unreachable block (ram,0x02a0ba70) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong GuildWar_Backend_PImpl_SessionImpl__get_authKey(long param_1)

{
  int iVar1;
  ushort uVar2;
  ushort uVar3;
  int iVar4;
  int iVar5;
  ulong uVar6;
  long extraout_x1;
  undefined8 *puVar7;
  byte bVar8;
  long lVar9;
  undefined8 *puVar10;
  long lVar11;
  undefined8 uVar12;
  long lVar13;
  undefined8 uVar14;
  
  if ((bRam00000000049fbdf0 & 1) == 0) {
    func_0x010c65d4(0xc03c);
    bRam00000000049fbdf0 = 1;
  }
  lVar11 = *(long *)(param_1 + 0x60);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  if ((bRam00000000049fc607 & 1) == 0) {
    func_0x010c65d4(0x91b4,lVar11,0);
    bRam00000000049fc607 = 1;
  }
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  if ((bRam00000000049fc608 & 1) == 0) {
    func_0x010c65d4(0x91b2);
    bRam00000000049fc608 = 1;
  }
  if (lVar11 == 0) {
    return 0;
  }
  if ((bRam00000000049fc605 & 1) == 0) {
    func_0x010c65d4(0x91aa);
    bRam00000000049fc605 = 1;
  }
  if (*(char *)(lVar11 + 0x20) == '\0') {
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(lVar11 + 0x10) =
         **(undefined8 **)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xb8);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar12 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__InternalEncrypt
                       (_class_string_TypeInfo,**(undefined8 **)(_class_string_TypeInfo + 0xb8));
    *(undefined8 *)(lVar11 + 0x18) = uVar12;
    *(undefined1 *)(lVar11 + 0x20) = 1;
  }
  lVar13 = *(long *)(lVar11 + 0x10);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = System_String__IsNullOrEmpty(0,lVar13,0);
  if ((uVar6 & 1) != 0) {
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      uVar6 = func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
    }
    lVar13 = **(long **)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xb8);
  }
  uVar12 = *(undefined8 *)(lVar11 + 0x18);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    uVar6 = func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
  }
  lVar11 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__GetString(uVar6,uVar12);
  if ((bRam00000000049fc5fe & 1) == 0) {
    func_0x010c65d4(0x91a3);
    bRam00000000049fc5fe = 1;
  }
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = System_String__IsNullOrEmpty(0,lVar11,0);
  if ((uVar6 & 1) != 0) {
    if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    return **(ulong **)(_class_string_TypeInfo + 0xb8);
  }
  if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar6 = System_String__IsNullOrEmpty(0,lVar13,0);
  if ((uVar6 & 1) != 0) {
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar13 = **(long **)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xb8);
  }
  if (lVar13 == 0) {
    func_0x010eec64(0);
  }
  iVar4 = System_String__get_Length(lVar13,0);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  iVar5 = System_String__get_Length(lVar11,0);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
  }
  lVar9 = *(long *)(*(long *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo +
                             0xb8) + 8);
  if (lVar9 != 0) {
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
      lVar9 = *(long *)(*(long *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo +
                                 0xb8) + 8);
      if (lVar9 == 0) {
        lVar11 = func_0x010eec64(0);
        if ((bRam00000000049fc5ff & 1) == 0) {
          func_0x010c65d4(0x91a1);
          bRam00000000049fc5ff = 1;
        }
        uVar12 = *(undefined8 *)(lVar11 + 0x10);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) &
             1) != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0))
        {
          func_0x010da55c();
        }
        uVar14 = **(undefined8 **)
                   (_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xb8);
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = System_String__op_Inequality(0,uVar12,uVar14,0);
        if ((uVar6 & 1) != 0) {
          uVar14 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__InternalDecrypt(lVar11);
          uVar12 = uVar14;
          if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a)
               & 1) != 0) &&
             (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
            uVar12 = func_0x010da55c(
                                    _class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo
                                    );
          }
          uVar6 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__InternalEncrypt(uVar12,uVar14);
          *(ulong *)(lVar11 + 0x18) = uVar6;
          *(undefined8 *)(lVar11 + 0x10) =
               **(undefined8 **)
                 (_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xb8);
        }
        return uVar6;
      }
    }
    if (iVar5 <= *(int *)(lVar9 + 0x18)) goto code_r0x01c1d81c;
  }
  uVar12 = func_0x01101ad4(_class_char___TypeInfo,iVar5);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
  }
  *(undefined8 *)
   (*(long *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xb8) + 8) = uVar12
  ;
code_r0x01c1d81c:
  bVar8 = *(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1;
  if (0 < iVar5) {
    uVar6 = 0;
    do {
      if ((bVar8 != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
      }
      lVar9 = *(long *)(*(long *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo +
                                 0xb8) + 8);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = System_String__get_Chars(lVar11,uVar6 & 0xffffffff,0);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      iVar1 = 0;
      if (iVar4 != 0) {
        iVar1 = (int)uVar6 / iVar4;
      }
      uVar3 = System_String__get_Chars(lVar13,(int)uVar6 - iVar1 * iVar4,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar9 + 0x18) <= uVar6) {
        uVar12 = func_0x010ef850();
        func_0x010eebec(uVar12,0,0);
      }
      *(ushort *)(lVar9 + uVar6 * 2 + 0x20) = uVar3 ^ uVar2;
      uVar6 = uVar6 + 1;
      bVar8 = *(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) &
              1;
    } while (iVar5 != (int)uVar6);
  }
  if ((bVar8 != 0) &&
     (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
  }
  lVar11 = *(long *)(*(long *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo +
                              0xb8) + 8);
  if ((bRam0000000004a03aeb & 1) == 0) {
    func_0x010c65d4(0xccf2);
    bRam0000000004a03aeb = 1;
  }
  if (lVar11 == 0) {
    uVar12 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
    System_ArgumentNullException___ctor(uVar12,_str_value,0);
  }
  else {
    if (iVar5 < 0) {
      uVar12 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      puVar7 = (undefined8 *)&str_length;
      puVar10 = (undefined8 *)&str_Cannot_be_negative_;
    }
    else {
      if (-1 < *(int *)(lVar11 + 0x18) - iVar5) {
        if (iVar5 == 0) {
          if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar6 = **(ulong **)(_class_string_TypeInfo + 0xb8);
        }
        else {
          if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar6 = func_0x01103084(iVar5);
          iVar4 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
          lVar13 = 0;
          if (*(int *)(lVar11 + 0x18) != 0) {
            lVar13 = lVar11 + 0x20;
          }
          lVar11 = _class_string_TypeInfo;
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            lVar11 = func_0x010da55c();
          }
          System_String__CharCopy(lVar11,uVar6 + (long)iVar4,lVar13,iVar5);
        }
        return uVar6;
      }
      uVar12 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      puVar7 = (undefined8 *)&str_startIndex;
      puVar10 = (undefined8 *)&str_Cannot_be_negative__and_should_be_less_than_length_of_string_;
    }
    System_ArgumentOutOfRangeException___ctor(uVar12,*puVar7,*puVar10,0);
  }
  func_0x010eebec(uVar12,0,_mm_Method_System_String_CreateString__);
  func_0x0112148c();
  if ((bRam0000000004a03aec & 1) == 0) {
    func_0x010c65d4(0xccee);
    bRam0000000004a03aec = 1;
  }
  if (extraout_x1 == 0) {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
  }
  else {
    uVar6 = *(ulong *)(extraout_x1 + 0x18);
    if ((int)uVar6 != 0) {
      if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
        uVar6 = *(ulong *)(extraout_x1 + 0x18);
      }
      uVar6 = func_0x01103084(uVar6 & 0xffffffff);
      iVar4 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
      uVar12 = *(undefined8 *)(extraout_x1 + 0x18);
      lVar11 = 0;
      if ((int)uVar12 != 0) {
        lVar11 = extraout_x1 + 0x20;
      }
      lVar13 = _class_string_TypeInfo;
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        lVar13 = func_0x010da55c();
        uVar12 = *(undefined8 *)(extraout_x1 + 0x18);
      }
      System_String__CharCopy(lVar13,uVar6 + (long)iVar4,lVar11,uVar12);
      return uVar6;
    }
    if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
  }
  return **(ulong **)(_class_string_TypeInfo + 0xb8);
}


