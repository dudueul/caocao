namespace HutongGames.PlayMaker.Actions
{
	public class SetVector2Value : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		[RequiredField]
		public FsmVector2 vector2Value;

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
