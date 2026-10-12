namespace HutongGames.PlayMaker.Actions
{
	public class SetIntValue : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

		[RequiredField]
		public FsmInt intValue;

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
