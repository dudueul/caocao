namespace HutongGames.PlayMaker.Actions
{
	public class IntAdd : FsmStateAction
	{
		[RequiredField]
		public FsmInt intVariable;

		[RequiredField]
		public FsmInt add;

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
