// name: GuildWar.Backend.PImpl.SessionImpl$$RequestGetWorldData
// entry: 01b2f6ac

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__RequestGetWorldData
               (long *param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4,
               undefined4 param_5,long param_6,long param_7)

{
  ushort uVar1;
  int iVar2;
  uint uVar3;
  long lVar4;
  long *plVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar11;
  ulong uVar12;
  undefined8 *puVar13;
  int *piVar14;
  undefined4 uStack_78;
  undefined4 uStack_74;
  undefined8 uStack_70;
  undefined4 uStack_68;
  undefined4 uStack_64;
  
  if ((bRam00000000049fbe04 & 1) == 0) {
    func_0x010c65d4(0xc02d);
    bRam00000000049fbe04 = 1;
  }
  lVar4 = (**(code **)(*param_1 + 0x1a0))(param_1,*(undefined8 *)(*param_1 + 0x1a8));
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = System_String__get_Length(lVar4,0);
  if (iVar2 != 0) {
    if (param_6 == 0) {
      lVar4 = 0;
    }
    else {
      lVar4 = func_0x010fdda8(_class_System_Collections_Generic_List<int>_TypeInfo);
      System_Collections_Generic_List<int>___ctor
                (lVar4,_mm_Method_System_Collections_Generic_List<int>__ctor__);
    }
    plVar5 = (long *)func_0x01101ad4(_class_object___TypeInfo,0xc);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((_str_clan_db_shard != 0) &&
       (lVar6 = func_0x010fdfcc(_str_clan_db_shard,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    lVar6 = _str_clan_db_shard;
    if ((int)plVar5[3] == 0) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[4] = lVar6;
    uStack_64 = param_2;
    lVar6 = func_0x010fdd08(_class_int_TypeInfo,&uStack_64);
    if ((lVar6 != 0) && (lVar8 = func_0x010fdfcc(lVar6,*(undefined8 *)(*plVar5 + 0x40)), lVar8 == 0)
       ) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    if (*(uint *)(plVar5 + 3) < 2) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[5] = lVar6;
    if ((_str_world_id != 0) &&
       (lVar6 = func_0x010fdfcc(_str_world_id,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    lVar6 = _str_world_id;
    if (*(uint *)(plVar5 + 3) < 3) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[6] = lVar6;
    uStack_68 = param_3;
    lVar6 = func_0x010fdd08(_class_int_TypeInfo,&uStack_68);
    if ((lVar6 != 0) && (lVar8 = func_0x010fdfcc(lVar6,*(undefined8 *)(*plVar5 + 0x40)), lVar8 == 0)
       ) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    if (*(uint *)(plVar5 + 3) < 4) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[7] = lVar6;
    if ((_str_guild_id != 0) &&
       (lVar6 = func_0x010fdfcc(_str_guild_id,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    lVar6 = _str_guild_id;
    if (*(uint *)(plVar5 + 3) < 5) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[8] = lVar6;
    lVar6 = MyGuildData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = GuildData__get_info(lVar6,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uStack_70 = GuildInfo__get_guildId(lVar6,0);
    lVar6 = func_0x010fdd08(_class_ulong_TypeInfo,&uStack_70);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar6 != 0) && (lVar8 = func_0x010fdfcc(lVar6,*(undefined8 *)(*plVar5 + 0x40)), lVar8 == 0)
       ) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    if (*(uint *)(plVar5 + 3) < 6) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[9] = lVar6;
    if ((_str_from_seq != 0) &&
       (lVar6 = func_0x010fdfcc(_str_from_seq,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    lVar6 = _str_from_seq;
    if (*(uint *)(plVar5 + 3) < 7) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[10] = lVar6;
    uStack_74 = param_4;
    lVar6 = func_0x010fdd08(_class_int_TypeInfo,&uStack_74);
    if ((lVar6 != 0) && (lVar8 = func_0x010fdfcc(lVar6,*(undefined8 *)(*plVar5 + 0x40)), lVar8 == 0)
       ) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    if (*(uint *)(plVar5 + 3) < 8) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[0xb] = lVar6;
    if ((_str_to_seq != 0) &&
       (lVar6 = func_0x010fdfcc(_str_to_seq,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    lVar6 = _str_to_seq;
    if (*(uint *)(plVar5 + 3) < 9) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[0xc] = lVar6;
    uStack_78 = param_5;
    lVar6 = func_0x010fdd08(_class_int_TypeInfo,&uStack_78);
    if ((lVar6 != 0) && (lVar8 = func_0x010fdfcc(lVar6,*(undefined8 *)(*plVar5 + 0x40)), lVar8 == 0)
       ) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    if (*(uint *)(plVar5 + 3) < 10) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[0xd] = lVar6;
    if ((_str_except_seq != 0) &&
       (lVar6 = func_0x010fdfcc(_str_except_seq,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    lVar6 = _str_except_seq;
    if (*(uint *)(plVar5 + 3) < 0xb) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[0xe] = lVar6;
    if ((lVar4 != 0) && (lVar6 = func_0x010fdfcc(lVar4,*(undefined8 *)(*plVar5 + 0x40)), lVar6 == 0)
       ) {
      uVar7 = func_0x010efe90();
      func_0x010eebec(uVar7,0,0);
    }
    if (*(uint *)(plVar5 + 3) < 0xc) {
      uVar7 = func_0x010ef850();
      func_0x010eebec(uVar7,0,0);
    }
    plVar5[0xf] = lVar4;
    lVar4 = (**(code **)(*param_1 + 0x170))(param_1,*(undefined8 *)(*param_1 + 0x178));
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = GuildWar_Backend_ConnectionInfo__get_distributorServerUri(lVar4);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar7 = System_String__Concat(0,_str_http___,uVar7,_str__guildWar_getWorldData,0);
    uVar9 = GuildWar_Backend_PImpl_SessionImpl__addUserIdObjects(uVar7,plVar5);
    if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_HttpRequest_TypeInfo);
    }
    uVar9 = HttpRequest__PacketArrayToPacketString(0,uVar9,0);
    uVar10 = func_0x010fdda8(_class_HttpRequest_HttpRequestContext_TypeInfo);
    HttpRequest_HttpRequestContext___ctor(uVar10,uVar7,uVar9,_str_POST,0,param_7,0,0,0);
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar4 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    HttpRequest__Send(lVar4,uVar10,0);
    return;
  }
  if (param_7 == 0) {
    func_0x010eec64(0);
  }
  if (*(long *)(param_7 + 0x58) != 0) {
    HttpRequest_ResponseCallback__Invoke(*(long *)(param_7 + 0x58),0,0);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(param_7 + 0x10);
  plVar5 = *(long **)(param_7 + 0x20);
  puVar13 = *(undefined8 **)(param_7 + 0x28);
  func_0x010da364(puVar13);
  uVar3 = func_0x01101d6c(puVar13);
  if ((uVar3 & 1) == 0) {
    if (*(char *)((long)puVar13 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x024f9e44. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar5,0,puVar13);
      return;
    }
    plVar5 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar13 + 0x4e) != '\x01') {
      if ((*(short *)((long)puVar13 + 0x4c) != -1) &&
         (((plVar5 == (long *)0x0 || ((*(byte *)(*plVar5 + 0x10d) & 1) == 0)) &&
          (*(long *)(param_7 + 0x18) != 0)))) {
        uVar11 = func_0x01101d58(puVar13);
        func_0x01101dd4(puVar13);
        uVar12 = func_0x010ba65c();
        if ((uVar11 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar13 + 0x4c);
          if ((uVar12 & 1) == 0) {
            lVar4 = lRam0000000000000000 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x130);
            puVar13 = *(undefined8 **)(lVar4 + 0x138);
          }
          else {
            lVar4 = func_0x01101dd4(puVar13);
            if ((ulong)*(ushort *)(lRam0000000000000000 + 0x122) != 0) {
              uVar11 = 0;
              piVar14 = (int *)(*(long *)(lRam0000000000000000 + 0xb0) + 8);
              do {
                if (*(long *)(piVar14 + -2) == lVar4) {
                  puVar13 = (undefined8 *)
                            (lRam0000000000000000 + (ulong)(*piVar14 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x024fa024;
                }
                uVar11 = uVar11 + 1;
                piVar14 = piVar14 + 4;
              } while (uVar11 < *(ushort *)(lRam0000000000000000 + 0x122));
            }
            puVar13 = (undefined8 *)func_0x010bc584(0,lVar4,(ulong)uVar1);
code_r0x024fa024:
            UNRECOVERED_JUMPTABLE = (code *)*puVar13;
            puVar13 = (undefined8 *)puVar13[1];
          }
        }
        else {
          if ((uVar12 & 1) == 0) {
            uVar7 = *(undefined8 *)
                     (lRam0000000000000000 + (ulong)*(ushort *)((long)puVar13 + 0x4c) * 0x10 + 0x138
                     );
          }
          else {
            if ((ulong)*(ushort *)(lRam0000000000000000 + 0x122) != 0) {
              uVar11 = 0;
              piVar14 = (int *)(*(long *)(lRam0000000000000000 + 0xb0) + 8);
              do {
                if (*(long *)(piVar14 + -2) == puVar13[3]) {
                  lVar4 = lRam0000000000000000 +
                          (ulong)(*piVar14 + (uint)*(ushort *)((long)puVar13 + 0x4c)) * 0x10 + 0x130
                  ;
                  goto code_r0x024f9fd4;
                }
                uVar11 = uVar11 + 1;
                piVar14 = piVar14 + 4;
              } while (uVar11 < *(ushort *)(lRam0000000000000000 + 0x122));
            }
            lVar4 = func_0x010bc584(0);
code_r0x024f9fd4:
            uVar7 = *(undefined8 *)(lVar4 + 8);
          }
          puVar13 = (undefined8 *)func_0x010da294(uVar7,puVar13);
          UNRECOVERED_JUMPTABLE = (code *)*puVar13;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x024f9e88. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,puVar13);
      return;
    }
    if (((*(short *)((long)puVar13 + 0x4c) != -1) &&
        ((plVar5 == (long *)0x0 || ((*(byte *)(*plVar5 + 0x10d) & 1) == 0)))) &&
       (*(long *)(param_7 + 0x18) != 0)) {
      uVar11 = func_0x01101d58(puVar13);
      func_0x01101dd4(puVar13);
      uVar12 = func_0x010ba65c();
      if ((uVar11 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar13 + 0x4c);
        if ((uVar12 & 1) == 0) {
          lVar4 = *plVar5 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x130);
          puVar13 = *(undefined8 **)(lVar4 + 0x138);
        }
        else {
          lVar4 = func_0x01101dd4(puVar13);
          lVar6 = *plVar5;
          if ((ulong)*(ushort *)(lVar6 + 0x122) != 0) {
            uVar11 = 0;
            piVar14 = (int *)(*(long *)(lVar6 + 0xb0) + 8);
            do {
              if (*(long *)(piVar14 + -2) == lVar4) {
                puVar13 = (undefined8 *)(lVar6 + (ulong)(*piVar14 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x024fa004;
              }
              uVar11 = uVar11 + 1;
              piVar14 = piVar14 + 4;
            } while (uVar11 < *(ushort *)(lVar6 + 0x122));
          }
          puVar13 = (undefined8 *)func_0x010bc584(plVar5,lVar4,(ulong)uVar1);
code_r0x024fa004:
          UNRECOVERED_JUMPTABLE = (code *)*puVar13;
          puVar13 = (undefined8 *)puVar13[1];
        }
      }
      else {
        if ((uVar12 & 1) == 0) {
          uVar7 = *(undefined8 *)(*plVar5 + (ulong)*(ushort *)((long)puVar13 + 0x4c) * 0x10 + 0x138)
          ;
        }
        else {
          lVar4 = *plVar5;
          if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
            uVar11 = 0;
            piVar14 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
            do {
              if (*(long *)(piVar14 + -2) == puVar13[3]) {
                lVar4 = lVar4 + (ulong)(*piVar14 + (uint)*(ushort *)((long)puVar13 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x024f9fa0;
              }
              uVar11 = uVar11 + 1;
              piVar14 = piVar14 + 4;
            } while (uVar11 < *(ushort *)(lVar4 + 0x122));
          }
          lVar4 = func_0x010bc584(plVar5);
code_r0x024f9fa0:
          uVar7 = *(undefined8 *)(lVar4 + 8);
        }
        puVar13 = (undefined8 *)func_0x010da294(uVar7,puVar13);
        UNRECOVERED_JUMPTABLE = (code *)*puVar13;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x024f9e68. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar5,0,puVar13);
  return;
}


