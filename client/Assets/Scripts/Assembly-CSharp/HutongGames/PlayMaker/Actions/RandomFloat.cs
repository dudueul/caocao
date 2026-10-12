namespace HutongGames.PlayMaker.Actions
{
	public class RandomFloat : FsmStateAction
	{
		[RequiredField]
		public FsmFloat min;

		[RequiredField]
		public FsmFloat max;

		[RequiredField]
		public FsmFloat storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
