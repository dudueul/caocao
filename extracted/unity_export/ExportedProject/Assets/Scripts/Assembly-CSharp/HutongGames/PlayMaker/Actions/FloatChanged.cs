namespace HutongGames.PlayMaker.Actions
{
	public class FloatChanged : FsmStateAction
	{
		[RequiredField]
		public FsmFloat floatVariable;

		public FsmEvent changedEvent;

		public FsmBool storeResult;

		private float previousValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
