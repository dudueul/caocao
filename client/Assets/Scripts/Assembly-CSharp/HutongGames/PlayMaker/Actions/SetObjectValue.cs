namespace HutongGames.PlayMaker.Actions
{
	public class SetObjectValue : FsmStateAction
	{
		[RequiredField]
		public FsmObject objectVariable;

		[RequiredField]
		public FsmObject objectValue;

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
	}
}
