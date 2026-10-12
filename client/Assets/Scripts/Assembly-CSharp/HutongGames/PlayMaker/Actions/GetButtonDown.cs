namespace HutongGames.PlayMaker.Actions
{
	public class GetButtonDown : FsmStateAction
	{
		[RequiredField]
		public FsmString buttonName;

		public FsmEvent sendEvent;

		public FsmBool storeResult;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
