import React from "react";
import { connect } from "dva";
import { Button, Col, Form, Input, Row } from "antd";
import DocumentTitle from "react-document-title";
import ErrorTips from "../../components/errors/error-tips";

@connect(
  (state) => ({ ...state.account, error: state.error })
)
class SignUpWithPasswordPage extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!errors) {
        this.props.dispatch({
          type: "account/signupUserName",
          payload: {
            username: values["username"],
            password: values["password"]
          }
        });
      }
    });
  };

  validateRePassword = (rule, value, callback) => {
    if (value && value !== this.props.form.getFieldValue("password")) {
      callback("两次输入必须一致！");
    } else {
      callback();
    }
  };

  render() {
    const FormItem = Form.Item;

    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 }
    };

    const { getFieldProps } = this.props.form;

    const usernameProps = getFieldProps("username", {
      rules: [
        { required: true, pattern: /^(?=.*[a-zA-Z])(?=.*\d)[\s\S]{4,16}$/, message: "4到16个字符，至少有字母和数字" }
      ]
    });

    const passwordProps = getFieldProps("password", {
      rules: [
        { required: true, whitespace: true, min: 6, max: 20, message: "密码为6至20个字符" }
      ]
    });

    const rePasswordProps = getFieldProps("rePassword", {
      rules: [
        {
          required: true,
          whitespace: true,
          message: "请再次输入密码"
        },
        {
          validator: this.validateRePassword
        }
      ]
    });

    const errorTips = this.props.error["account"] ? <ErrorTips errors={this.props.error["account"]}/> : null;

    return (
      <DocumentTitle title='用户注册'>
        <Row>
          <Col lg={6} md={6} sm={0}/>
          <Col lg={12} md={12} sm={24}>
            <Form horizontal="true" onSubmit={this.handleSubmit}>
              <Row>
                <Col md={6}/>
                <Col md={14}>
                  {errorTips}
                </Col>
              </Row>
              <FormItem {...formItemLayout} label="用户名">
                <Input {...usernameProps} type="text" autoComplete="off"/>
              </FormItem>
              <FormItem {...formItemLayout} label="密码">
                <Input {...passwordProps} type="password" autoComplete="off"/>
              </FormItem>
              <FormItem {...formItemLayout} label="确认密码" autoComplete="off">
                <Input {...rePasswordProps} type="password"/>
              </FormItem>
              <FormItem wrapperCol={{ span: 16, offset: 6 }} style={{ marginTop: 24 }}>
                <Button type="primary" htmlType="submit">注册</Button>
              </FormItem>
            </Form>
          </Col>
          <Col lg={6} md={6} sm={0}>
          </Col>
        </Row>
      </DocumentTitle>
    );
  }
}

SignUpWithPasswordPage = Form.create()(SignUpWithPasswordPage);
export default SignUpWithPasswordPage;
