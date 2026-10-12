namespace HutongGames.PlayMaker.Actions
{
	public class GetTagCount : FsmStateAction
	{
		public FsmString tag;

		[RequiredField]
		public FsmInt storeResult;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
