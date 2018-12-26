import React from "react";
import { connect } from "dva";
import DocumentTitle from "react-document-title";
import { Col, Row } from "antd";

@connect(
  (state) => ({ ...state.userSession })
)
export default class SignInSuccessPage extends React.Component {
  render() {
    console.log(this.props);

    return (
      <DocumentTitle title='登录已成功'>
        <Row>
          <Col lg={6} md={6} sm={0}/>
          <Col lg={12} md={12} sm={24}>
            <Row>
              <Col md={6}/>
              <Col md={14}>
                {`账号${this.props.id}已登录成功`}
              </Col>
            </Row></Col>
          <Col lg={6} md={6} sm={0}/>
        </Row>
      </DocumentTitle>
    );
  }
}
