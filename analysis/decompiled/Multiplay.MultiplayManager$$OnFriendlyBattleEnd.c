// name: Multiplay.MultiplayManager$$OnFriendlyBattleEnd
// entry: 013a8a94

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnFriendlyBattleEnd(long param_1,long param_2)

{
  byte bVar1;
  undefined4 uVar2;
  int iVar3;
  ulong uVar4;
  long lVar5;
  long lVar6;
  long lVar7;
  long *plVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  long lVar11;
  int iVar12;
  long lVar13;
  long lVar14;
  
  if ((bRam00000000049f7b0e & 1) == 0) {
    func_0x010c65d4(0x8a52);
    bRam00000000049f7b0e = 1;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar4 & 1) != 0) {
    Multiplay_MultiplayManager__LeaveWatch(param_1);
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = LitJson_JsonData__get_Item(lVar5,_str_roomInfo,0);
  if (lVar5 == 0) {
    if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar9 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  }
  else {
    if (((*(ushort *)(_class_string_TypeInfo + 0x129) & 0x100) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar9 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar9 = Util__GetData(0,lVar5,_str_roomId,uVar9,0);
  }
  lVar6 = CompetitionManager__get_instance(0,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = *(long *)(lVar6 + 0xd8);
  if (lVar6 == 0) {
    return;
  }
  uVar10 = *(undefined8 *)(lVar6 + 0x10);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = System_String__op_Equality(0,uVar10,uVar9,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (*(int *)(lVar6 + 0x38) != 1) {
    return;
  }
  if ((bRam00000000049fd66f & 1) == 0) {
    func_0x010c65d4(0x5850,lVar5,0);
    bRam00000000049fd66f = 1;
  }
  uVar9 = *(undefined8 *)(lVar6 + 0x10);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar9 = Util__GetData(0,lVar5,_str_roomId,uVar9,0);
  *(undefined8 *)(lVar6 + 0x10) = uVar9;
  uVar2 = Util__GetData(0,lVar5,_str_battleId,*(undefined4 *)(lVar6 + 0x18),0);
  *(undefined4 *)(lVar6 + 0x18) = uVar2;
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = LitJson_JsonData__get_Item(lVar5,_str_members,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
    iVar3 = LitJson_JsonData__get_Count(0,0);
    if (iVar3 < 1) goto code_r0x01de74c8;
  }
  lVar11 = *(long *)(lVar6 + 0x20);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<tk2dUICamera>__Clear
            (lVar11,_mm_Method_System_Collections_Generic_List<FriendlyMatchingRoomMember>_Clear__);
  lVar11 = *(long *)(lVar6 + 0x28);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<ulong,_UserTreasureSocket>__Clear
            (lVar11,
             _mm_Method_System_Collections_Generic_Dictionary<ulong,_FriendlyMatchingRoomMember>_Clear__
            );
  *(long *)(lVar6 + 0x48) = 0;
  *(undefined8 *)(lVar6 + 0x58) = 0;
  *(undefined8 *)(lVar6 + 0x60) = 0;
  *(undefined8 *)(lVar6 + 0x50) = 0;
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  iVar3 = LitJson_JsonData__get_Count(lVar7,0);
  if (0 < iVar3) {
    iVar12 = 0;
    do {
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      uVar9 = LitJson_JsonData__get_Item(lVar7,iVar12,0);
      lVar11 = func_0x010fdda8(_class_FriendlyMatchingRoomMember_TypeInfo);
      FriendlyMatchingRoomMember___ctor(lVar11,uVar9);
      lVar13 = *(long *)(lVar6 + 0x20);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUILayoutItem>__Add
                (lVar13,lVar11,
                 _mm_Method_System_Collections_Generic_List<FriendlyMatchingRoomMember>_Add__);
      lVar13 = *(long *)(lVar6 + 0x28);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar9 = *(undefined8 *)(lVar11 + 0x10);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_Dictionary<ulong,_UserUnit>__Add
                (lVar13,uVar9,lVar11,
                 _mm_Method_System_Collections_Generic_Dictionary<ulong,_FriendlyMatchingRoomMember>_Add__
                );
      if (*(int *)(lVar11 + 0x30) == 1) {
        *(long *)(lVar6 + 0x50) = lVar11;
      }
      else if (*(int *)(lVar11 + 0x30) == 0) {
        *(long *)(lVar6 + 0x48) = lVar11;
      }
      if (*(char *)(lVar11 + 0x28) != '\0') {
        *(long *)(lVar6 + 0x58) = lVar11;
      }
      lVar14 = *(long *)(lVar11 + 0x10);
      lVar13 = MyUserData__get_instance(0,0);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      plVar8 = (long *)UserData__get_info(lVar13,0);
      if (plVar8 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar13 = (**(code **)(*plVar8 + 0x1a0))(plVar8,*(undefined8 *)(*plVar8 + 0x1a8));
      if (lVar14 == lVar13) {
        *(long *)(lVar6 + 0x60) = lVar11;
      }
      iVar12 = iVar12 + 1;
    } while (iVar3 != iVar12);
  }
  uVar9 = _mm_Method_FriendlyMatchingRoomMember_CompareFunc__;
  lVar11 = *(long *)(lVar6 + 0x20);
  lVar7 = *(long *)(_class_FriendlyMatchingRoom_TypeInfo + 0xb8);
  if (*(long *)(lVar7 + 8) == 0) {
    uVar10 = func_0x010fdda8(_class_System_Comparison<FriendlyMatchingRoomMember>_TypeInfo);
    System_Comparison<tk2dUICamera>___ctor
              (uVar10,0,uVar9,_mm_Method_System_Comparison<FriendlyMatchingRoomMember>__ctor__);
    *(undefined8 *)(*(long *)(_class_FriendlyMatchingRoom_TypeInfo + 0xb8) + 8) = uVar10;
    lVar7 = *(long *)(_class_FriendlyMatchingRoom_TypeInfo + 0xb8);
  }
  uVar9 = *(undefined8 *)(lVar7 + 8);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<tk2dUICamera>__Sort
            (lVar11,uVar9,
             _mm_Method_System_Collections_Generic_List<FriendlyMatchingRoomMember>_Sort__);
code_r0x01de74c8:
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = LitJson_JsonData__get_Item(lVar5,_str_fairRule,0);
  FriendlyMatchingRoom__DeserializeFairRule(lVar6,uVar9);
  uVar2 = *(undefined4 *)(lVar6 + 0x3c);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Util__GetData(0,lVar5,_str_maxMemberCount,uVar2,0);
  *(undefined4 *)(lVar6 + 0x3c) = uVar2;
  uVar2 = Util__GetData<EnumData_FriendlyMatchingRoomState>
                    (0,lVar5,_str_state,*(undefined4 *)(lVar6 + 0x38),
                     _mm_Method_Util_GetData<EnumData_FriendlyMatchingRoomState>__);
  *(undefined4 *)(lVar6 + 0x38) = uVar2;
  bVar1 = Util__GetData(0,lVar5,_str_isChangingMap,*(undefined1 *)(lVar6 + 0x40),0);
  *(byte *)(lVar6 + 0x40) = bVar1 & 1;
  return;
}


