// name: PacketUtil$$JsonString
// entry: 016aa894

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 PacketUtil__JsonString(undefined8 param_1,long param_2)

{
  ulong uVar1;
  uint uVar2;
  byte bVar3;
  long lVar4;
  long *plVar5;
  long lVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  uint uVar9;
  undefined8 uStack_60;
  undefined8 uStack_58;
  
  if ((bRam00000000049f961a & 1) == 0) {
    func_0x010c65d4(0x933a);
    bRam00000000049f961a = 1;
  }
  uStack_60 = 0;
  if ((param_2 == 0) || (*(int *)(param_2 + 0x18) < 1)) {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  }
  else {
    if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar4 = **(long **)(_class_PacketUtil_TypeInfo + 0xb8);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    System_Text_StringBuilder__set_Length(lVar4,0,0);
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__Reset(lVar4,0);
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WriteObjectStart(lVar4,0);
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WritePropertyName(lVar4,_str_version,0);
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    if (((*(byte *)(_class_CurrentBundleVersion_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_CurrentBundleVersion_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = **(undefined8 **)(_class_CurrentBundleVersion_TypeInfo + 0xb8);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__Write(lVar4,uVar7,0);
    if (*(int *)(param_2 + 0x18) == 0) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5 = *(long **)(param_2 + 0x20);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar1 = (**(code **)(*plVar5 + 0x130))(plVar5,_str_uid,*(undefined8 *)(*plVar5 + 0x138));
    if ((uVar1 & 1) != 0) {
      if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      LitJson_JsonWriter__WritePropertyName(lVar4,_str_uid,0);
      lVar6 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
      lVar4 = MyUserData__get_instance(0,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      plVar5 = (long *)UserData__get_info(lVar4,0);
      if (plVar5 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uStack_60 = (**(code **)(*plVar5 + 0x1a0))(plVar5,*(undefined8 *)(*plVar5 + 0x1a8));
      uVar7 = func_0x02d2a574(&uStack_60,0);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      LitJson_JsonWriter__Write(lVar6,uVar7,0);
    }
    uVar7 = *(undefined8 *)(param_2 + 0x18);
    bVar3 = *(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1;
    if (0 < (int)uVar7) {
      uVar9 = 0;
      do {
        uVar2 = (uint)uVar7;
        if ((bVar3 != 0) && (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
          uVar2 = (uint)*(undefined8 *)(param_2 + 0x18);
        }
        lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
        if (uVar2 <= uVar9) {
          uVar7 = func_0x010ef850();
          func_0x010eebec(uVar7,0,0);
        }
        plVar5 = *(long **)(param_2 + (long)(int)uVar9 * 8 + 0x20);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        if (plVar5 == (long *)0x0) {
code_r0x016aabc4:
          plVar5 = (long *)0x0;
        }
        else if (*plVar5 != _class_string_TypeInfo) {
          func_0x0111ef28(&uStack_58,*(undefined8 *)(*plVar5 + 0x40));
          uVar7 = func_0x010ef81c(uStack_58);
          func_0x010eebec(uVar7,0,0);
          func_0x036457e0(&uStack_58);
          goto code_r0x016aabc4;
        }
        LitJson_JsonWriter__WritePropertyName(lVar4,plVar5,0);
        if (*(uint *)(param_2 + 0x18) <= uVar9 + 1) {
          uVar7 = func_0x010ef850();
          func_0x010eebec(uVar7,0,0);
        }
        uVar8 = *(undefined8 *)(param_2 + (long)(int)(uVar9 + 1) * 8 + 0x20);
        uVar7 = *(undefined8 *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        LitJson_JsonMapper__ToJson(0,uVar8,uVar7,0);
        uVar7 = *(undefined8 *)(param_2 + 0x18);
        uVar9 = uVar9 + 2;
        bVar3 = *(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1;
      } while ((int)uVar9 < (int)uVar7);
    }
    if ((bVar3 != 0) && (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WriteObjectEnd(lVar4,0);
    plVar5 = (long *)**(undefined8 **)(_class_PacketUtil_TypeInfo + 0xb8);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar7 = (**(code **)(*plVar5 + 0x160))(plVar5,*(undefined8 *)(*plVar5 + 0x168));
  }
  return uVar7;
}


