namespace HutongGames.PlayMaker.Actions
{
	public class SetVector3Value : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		[RequiredField]
		public FsmVector3 vector3Value;

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
