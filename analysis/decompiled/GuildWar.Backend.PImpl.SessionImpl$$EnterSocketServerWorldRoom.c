// name: GuildWar.Backend.PImpl.SessionImpl$$EnterSocketServerWorldRoom
// entry: 01b2f0f8

void GuildWar_Backend_PImpl_SessionImpl__EnterSocketServerWorldRoom
               (long param_1,int param_2,int param_3)

{
  if (((((0 < param_2) && (0 < param_3)) &&
       ((*(undefined8 *)(param_1 + 0xb4) = 0, *(int *)(param_1 + 0xcc) != param_2 ||
        (*(int *)(param_1 + 0xd0) != param_3)))) &&
      ((*(int *)(param_1 + 0xbc) != param_2 || (*(int *)(param_1 + 0xc0) != param_3)))) &&
     ((*(int *)(param_1 + 0xac) != param_2 || (*(int *)(param_1 + 0xb0) != param_3)))) {
    *(int *)(param_1 + 0xac) = param_2;
    *(int *)(param_1 + 0xb0) = param_3;
  }
  return;
}


