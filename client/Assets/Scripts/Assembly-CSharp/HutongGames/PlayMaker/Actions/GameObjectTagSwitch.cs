namespace HutongGames.PlayMaker.Actions
{
	public class GameObjectTagSwitch : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		public FsmString[] compareTo;

		public FsmEvent[] sendEvent;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoTagSwitch()
		{
		}
	}
}
